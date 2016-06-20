package it.codegen.impl;

import it.codegen.PatchManager;
import it.codegen.DataManager;
import it.codegen.RepoManager;
import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import it.codegen.data.PatchResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by thilanka on 6/20/2016.
 */
public class PatchManagerImpl implements PatchManager
{
    @Autowired DataManager dataManager;

    @Autowired RepoManager repoManager;

    @Override public List<Patch> searchPatches( PatchSearchCriteria criteria )
    {
        return dataManager.searchPatches( criteria );
    }

    @Override public List<PatchResult> createPatches( List<String> revisions )
    {
        repoManager.createPatchs();
        return null;
    }
}
