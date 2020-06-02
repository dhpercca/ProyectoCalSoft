
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editorial {
    private int id_editorial;
    private String editorial;
    conexion_biblioteca mc;
    
    editorial(int id,String ed)
    {   
        id_editorial=id;
        editorial=ed;
        mc=new conexion_biblioteca();
    }
    Object[][] getEditorial()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM editorial";
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int i=0;
            while(rs.next())
            {   regs[i][0]=rs.getInt("id_editorial");
                regs[i][1]=rs.getString("editorial");
                i++;
            }
            mc.desconectar();
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
        return regs;
    }
}
