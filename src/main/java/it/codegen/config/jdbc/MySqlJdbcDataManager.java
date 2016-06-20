package it.codegen.config.jdbc;

import it.codegen.config.DataConfig;
import it.codegen.DataManager;
import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thilanka on 5/24/2016.
 */
@Component
public class MySqlJdbcDataManager implements DataManager
{
    @Autowired
    private DataConfig dataConfig;

    @Override public List<Patch> searchPatches( PatchSearchCriteria criteria )
    {
        List<Patch> patches = new ArrayList<>();
        StringBuilder sql = new StringBuilder( "SELECT * FROM tbx_patch where 1=1 " );
        if( !StringUtils.isEmpty( criteria.getAuthor() ) )
        {
            sql.append( "and author = ?" );
        }

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try
        {
            con = dataConfig.dataSource().getConnection();
            ps = con.prepareStatement( sql.toString() );

            int count = 1;
            if( !StringUtils.isEmpty( criteria.getAuthor() ) )
            {
                ps.setString( count++, criteria.getAuthor() );
            }

            rs = ps.executeQuery();
            while( rs.next() )
            {
                Patch patch = new Patch();
                patch.load( rs );

                patches.add( patch );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtility.close( rs );
            DBUtility.close( ps );
            DBUtility.close( con );
        }

        return patches;
    }
}
