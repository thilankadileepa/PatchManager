package it.codegen.svn;

import it.codegen.config.AppConfig;
import it.codegen.DataManager;
import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by thilanka on 5/24/2016.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Ignore
public class DataSourceTest extends TestCase
{
    @Autowired
    private DataManager dataManager;

    @Test public void testData()
    {
        List<Patch> patches = dataManager.searchPatches(new PatchSearchCriteria());
        assertTrue( patches.size() > 0 );
    }
}
