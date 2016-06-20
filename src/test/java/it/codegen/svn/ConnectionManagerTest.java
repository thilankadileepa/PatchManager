package it.codegen.svn;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.util.SVNPathUtil;
import org.tmatesoft.svn.core.internal.wc.patch.SVNPatch;
import org.tmatesoft.svn.core.internal.wc.patch.SVNPatchTarget;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaProcessor;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by thilanka on 5/9/2016.
 */
@Ignore
public class ConnectionManagerTest extends TestCase
{
    //    private static String svnUrl = "svn+ssh://192.168.0.6/usr/local/svnroot/branches/FDM_26062015_BRANCH/travelboxv3/codegen";
    private static String svnBase = "svn+ssh://192.168.0.6/usr/local/svnroot";
    private static String svnUrl = svnBase + "/branches/FDM_27022010_BRANCH/travelboxv3/codegen";
    private static String name = "thilanka";
    private static String password = "thilanka321";

    //        long startRevision = 528513;
    long startRevision = 571752;
    //        long endRevision = 528515;
    //    long endRevision = 549413;
    long endRevision = 571753;

    @Test public void testConnection()
    {
        try
        {
            SVNClientManager clientManager = getSVNClientManager();
            assertNotNull( clientManager );
        }
        catch( Exception e )
        {
            e.printStackTrace();
            fail();
        }
    }

    public void testPatch()
    {
        try
        {
            File original = new File("E:\\FDM_27022010_BRANCH_MAVEN\\travelboxv3\\codegen\\DataXmlFactory\\src\\it\\codegen\\tbx\\documentmanager\\services\\dataxmlfactory\\codegen\\xmlbean\\Voucher\\VoucherMainXmlBean.java");
            File file = new File( "d:/final_patch.patch" );
            SVNClientManager clientManager = getSVNClientManager();
            clientManager.getDiffClient().doPatch( original, file, true, 1 );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

    }

    @Test public void testDoDiff()
    {

        try
        {

            SVNClientManager clientManager = getSVNClientManager();
            SVNDiffClient diffClient = clientManager.getDiffClient();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream result = new PrintStream( baos );

            SVNRevision sR = SVNRevision.create( startRevision );
            SVNRevision eR = SVNRevision.create( endRevision );

            //            diffClient.doDiff( SVNURL.parseURIEncoded( svnUrl + "/OnlineWeb/FDMWeb/WebContent/version.html" ), sR, sR, eR, SVNDepth.FILES, true, result );
            //            diffClient.doDiff( SVNURL.parseURIEncoded( svnUrl + "/AccomGateWay/src/it/codegen/tbx/accommodationgateway/touricoV3/TouricoController.java" ), sR, sR, eR, SVNDepth.FILES, true, result );
            diffClient.doDiff( SVNURL.parseURIEncoded( svnUrl + "/DataXmlFactory/src/it/codegen/tbx/documentmanager/services/dataxmlfactory/codegen/xmlbean/Voucher/VoucherMainXmlBean.java" ), sR, sR, eR, SVNDepth.FILES, true, result );
            String resultString = baos.toString();
            System.out.println( "Result :" + resultString );

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

    @Test public void testRepository()
    {

        try
        {
            SVNRepository repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( svnUrl ) );
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name, password );
            repository.setAuthenticationManager( authManager );
            System.out.println( "Latest Revision :" + repository.getLatestRevision() );

            //            Collection logEntries = repository.log( new String[]{"/branches/FDM_26062015_BRANCH/travelboxv3/codegen/OnlineWeb/FDMWeb/WebContent/version.html"}, null, startRevision, endRevision, true, true );
            Collection logEntries = repository.log( new String[]{""}, null, startRevision, endRevision, true, true );
            for( Iterator entries = logEntries.iterator(); entries.hasNext(); )
            {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                System.out.println( String.format( "revision: %d, date %s", logEntry.getRevision(), logEntry.getDate() ) );

                logEntry.getChangedPaths().forEach( ( k, v ) -> System.out.println( v.getPath() ) );
                System.out.println( "----------------------------" );
            }

        }
        catch( SVNException e )
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test public void testCreatePatch()
    {

        try
        {
            SVNRepository repository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded( svnUrl ) );
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name, password );
            repository.setAuthenticationManager( authManager );

            SVNClientManager clientManager = getSVNClientManager();
            SVNDiffClient diffClient = clientManager.getDiffClient();


            SVNRevision sR = SVNRevision.create( startRevision );
            SVNRevision eR = SVNRevision.create( endRevision );

            Collection logEntries = repository.log( new String[]{""}, null, startRevision, endRevision, true, true );

            StringBuilder sb = new StringBuilder();
            ByteArrayOutputStream baos = null;
            PrintStream result = null;
            for( Iterator entries = logEntries.iterator(); entries.hasNext(); )
            {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                for( SVNLogEntryPath path : logEntry.getChangedPaths().values() )
                {
                    baos = new ByteArrayOutputStream();
                    result = new PrintStream( baos );

                    System.out.println( String.format( "revision: %d, date %s", logEntry.getRevision(), logEntry.getDate() ) );
                    diffClient.doDiff( SVNURL.parseURIEncoded( svnBase + path.getPath() ), eR, sR, eR, SVNDepth.INFINITY, true, result );
                    String resultString = baos.toString();

                    sb.append( resultString ).append( "\n" );
                }

                logEntry.getChangedPaths().forEach( ( k, v ) -> System.out.println( v.getPath() ) );
                System.out.println( "----------------------------" );
            }

            System.out.println( "------------------- FINAL Patch ---------------" );
            System.out.println( sb.toString() );
            System.out.println( "--------------------END FINAL Patch --------------" );

        }
        catch( Exception e )
        {
            e.printStackTrace();
            fail();
        }
    }
}
