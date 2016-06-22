package it.codegen.impl;

import it.codegen.DataManager;
import it.codegen.PatchManager;
import it.codegen.RepoManager;
import it.codegen.config.DataConfig;
import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import it.codegen.data.PatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.InteractionCallback;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by thilanka on 6/20/2016.
 */
public class PatchManagerImpl implements PatchManager
{
    @Autowired DataManager dataManager;

    @Autowired RepoManager repoManager;

    @Autowired
    private DataConfig dataConfig;

    @Override public List<Patch> searchPatches( PatchSearchCriteria criteria )
    {
        return dataManager.searchPatches( criteria );
    }

    @Override public List<PatchResult> createPatches( String branch ,List<Patch> patches )
    {
        List<Long> revisions = new ArrayList<>(  );
        patches.forEach( item->revisions.add( item.getRevision() ) );

        revisions.sort( ( i1, i2 ) -> i1.compareTo( i2 ) );
        List<Long> boundries = getRevisionBoundaries( revisions );

        // first record
        repoManager.createPatch( branch, boundries.get( 0 )-1, boundries.get( 0 ) );
        for( int i=0;i<boundries.size()-1;i++)
        {
            repoManager.createPatch( branch, boundries.get( i ), boundries.get( i+1 ) );


        }

        return null;
    }

    private List<Long> getRevisionBoundaries( List<Long> revisions )
    {
        List<Long> boundries = new ArrayList<>();

        if( revisions.size() > 2 )
        {
            boundries.add( revisions.get( 0 ) );
            for( int i = 0; i < revisions.size() - 2; i++ )
            {
                if( !( ( revisions.get( i + 1 ) - revisions.get( i ) == 1 ) && ( revisions.get( i + 2 ) - revisions.get( i + 1 ) == 1 ) ) )
                {
                    boundries.add( revisions.get( i + 1 ) );
                }
            }
            boundries.add( revisions.get( revisions.size() - 1 ) );
        }
        else
        {
            boundries.addAll( revisions );
        }

        return boundries;
    }
}
