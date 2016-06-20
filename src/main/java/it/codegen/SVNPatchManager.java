package it.codegen;

import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;

import java.util.List;

/**
 * Created by thilanka on 6/20/2016.
 */
public interface SVNPatchManager
{
    public List<Patch> searchPatches( PatchSearchCriteria criteria);
}
