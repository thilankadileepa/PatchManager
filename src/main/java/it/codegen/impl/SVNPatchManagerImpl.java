package it.codegen.impl;

import it.codegen.SVNPatchManager;
import it.codegen.config.DataManager;
import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by thilanka on 6/20/2016.
 */
public class SVNPatchManagerImpl implements SVNPatchManager
{
    @Autowired DataManager dataManager;

    @Override public List<Patch> searchPatches( PatchSearchCriteria criteria )
    {
        return dataManager.searchPatches( criteria );
    }
}
