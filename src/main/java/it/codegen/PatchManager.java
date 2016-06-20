package it.codegen;

import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import it.codegen.data.PatchResult;

import java.util.List;

/**
 * Created by thilanka on 6/20/2016.
 */
public interface PatchManager
{
    public List<Patch> searchPatches( PatchSearchCriteria criteria);

    public List<PatchResult> createPatches(List<String> revisions);
}
