package it.codegen.impl;

import it.codegen.PatchManager;
import it.codegen.config.AppConfig;
import it.codegen.data.Patch;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thilanka on 6/21/2016.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PatchManagerImplTest extends TestCase
{
    @Test
    public void testCreatePatch()
    {
        List<Patch> patches = new ArrayList<>(  );
        patches.add( new Patch( 1, 571752)  );
        patches.add( new Patch( 2, 571757)  );
        patches.add( new Patch( 3, 571753)  );
//        patches.add( new Patch( 4, 577611)  );
//        patches.add( new Patch( 5, 577601)  );
//        patches.add( new Patch( 6, 577602)  );
//        patches.add( new Patch( 7, 577593)  );
//        patches.add( new Patch( 8, 577598)  );
//        patches.add( new Patch( 9, 577610)  );
//        patches.add( new Patch( 10, 577594)  );
//        patches.add( new Patch( 11, 577590)  );


        String branch = "branches/FDM_27022010_BRANCH/travelboxv3";
         patchManager.createPatches(branch, patches );
    }

    @Autowired PatchManager patchManager;
}
