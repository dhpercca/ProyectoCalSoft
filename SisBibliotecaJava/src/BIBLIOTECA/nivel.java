
package BIBLIOTECA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class nivel {
    private int id_nivel;
    private String nivel;
    conexion_biblioteca mc;
    
    nivel(int id,String niv)
    {   
        id_nivel=id;
        nivel=niv;
        mc=new conexion_biblioteca();
    }
    Object[][] getNivel()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM nivel";
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int i=0;
            while(rs.next())
            {   regs[i][0]=rs.getInt("id_nivel");
                regs[i][1]=rs.getString("nivel");
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
