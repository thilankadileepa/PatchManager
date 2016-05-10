package it.codegen.svn;

import junit.framework.TestCase;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by thilanka on 5/9/2016.
 */
public class ConnectionManagerTest extends TestCase
{
    private static String svnUrl = "svn+ssh://192.168.0.6/usr/local/svnroot/branches/FDM_26062015_BRANCH/travelboxv3/codegen";
    private static String name = "thilanka";
    private static String password = "thilanka321";

    @Test public void testConnection()
    {
        try
        {
            SVNClientManager clientManager = getSVNClientManager();
            assertNotNull( clientManager );

            SVNDiffClient diffClient = clientManager.getDiffClient();
            //TODO


        }
        catch( Exception e )
        {
            e.printStackTrace();
            fail();
        }
    }

    public static SVNClientManager getSVNClientManager() throws Exception
    {
        SVNURL url = SVNURL.parseURIEncoded( svnUrl );
        SVNRepository repository = SVNRepositoryFactory.create( url, null );
        ISVNOptions myOptions = SVNWCUtil.createDefaultOptions( true );

        ISVNAuthenticationManager myAuthManager = SVNWCUtil.createDefaultAuthenticationManager( name, password );
        repository.setAuthenticationManager( myAuthManager );

        SVNClientManager clientManager = SVNClientManager.newInstance( myOptions, myAuthManager );

        return clientManager;
    }

    @Test
    public void testRepository()
    {
        long startRevision = 528513;
        long endRevision = 528515;

        try
        {
            SVNRepository repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( svnUrl ) );
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name, password );
            repository.setAuthenticationManager( authManager );
            System.out.println("Latest Revision :" + repository.getLatestRevision());

            Collection logEntries = repository.log( new String[]{""}, null, startRevision, endRevision, true, true );
            for( Iterator entries = logEntries.iterator(); entries.hasNext(); )
            {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                System.out.println( String.format( "revision: %d, date %s", logEntry.getRevision(), logEntry.getDate() ) );

                logEntry.getChangedPaths().forEach( (k,v) -> System.out.println(v.getPath()) );
                System.out.println("----------------------------");
            }

        }
        catch( SVNException e )
        {
            e.printStackTrace();
            fail();
        }
    }
}
