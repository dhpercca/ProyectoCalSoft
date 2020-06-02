
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class estado {
    private int id_estado;
    private String estado;
    conexion_biblioteca mc;
    
    estado(int id,String est)
    {   
        id_estado=id;
        estado=est;
        mc=new conexion_biblioteca();
    }
    Object[][] getEstado()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM estado";
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int i=0;
            while(rs.next())
            {   regs[i][0]=rs.getInt("id_estado");
                regs[i][1]=rs.getString("estado");
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
