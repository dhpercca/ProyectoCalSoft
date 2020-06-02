
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class categoria {
    private int id_categoria;
    private String categoria;
    conexion_biblioteca mc;
    
    categoria(int id,String ca)
    {   
        id_categoria=id;
        categoria=ca;
        mc=new conexion_biblioteca();
    }
    Object[][] getCategoria()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM categoria";
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int i=0;
            while(rs.next())
            {   regs[i][0]=rs.getInt("id_categoria");
                regs[i][1]=rs.getString("categoria");
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
