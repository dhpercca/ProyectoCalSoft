
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cargo {
    private int id_cargo;
    private String cargo;
    conexion_biblioteca mc;
    
    cargo(int id,String car)
    {   
        id_cargo=id;
        cargo=car;
        mc=new conexion_biblioteca();
    }
    Object[][] getCargo()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM cargo";
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int i=0;
            while(rs.next())
            {   regs[i][0]=rs.getInt("id_cargo");
                regs[i][1]=rs.getString("cargo");
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
