package it.codegen.svn;

import it.codegen.RepoManager;
import it.codegen.config.DataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by thilanka on 6/20/2016.
 */
public class SVNKitManager implements RepoManager
{
    @Autowired private DataConfig dataConfig;

    private static SVNRepository repository = null;
    private static SVNClientManager clientManager = null;

    @PostConstruct public void init() throws SVNException
    {
        SVNURL url = SVNURL.parseURIEncoded( dataConfig.getSvnBase() );
        repository = SVNRepositoryFactory.create( url );

        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( dataConfig.getUserName(), dataConfig.getPassword() );
        repository.setAuthenticationManager( authManager );

        ISVNOptions svnOptions = SVNWCUtil.createDefaultOptions( true );
        clientManager = SVNClientManager.newInstance( svnOptions, authManager );
    }

    @Override public void createPatch( String branch, long startRevision, long endRevision )
    {
        try
        {
            SVNDiffClient diffClient = clientManager.getDiffClient();

            SVNRevision sR = SVNRevision.create( startRevision );
            SVNRevision eR = SVNRevision.create( endRevision );

            File file = new File( dataConfig.getPatchFileBase() + "\\" + endRevision + ".patch" );
            file.createNewFile();

            FileOutputStream fop = new FileOutputStream( file );

            String url = dataConfig.getSvnBase() + branch;
            diffClient.doDiff( SVNURL.parseURIEncoded( url ), eR, sR, eR, SVNDepth.INFINITY, true, fop );

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
