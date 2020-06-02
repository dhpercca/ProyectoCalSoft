
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class autor {
    private int id_autor;
    private String autor;
    conexion_biblioteca mc;
    
    autor(int id,String au)
    {   
        id_autor=id;
        autor=au;
        mc=new conexion_biblioteca();
    }
    Object[][] getAutor()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM autor";
        try
        {   PreparedStatement pstm = mc.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int i=0;
            while(rs.next())
            {   regs[i][0]=rs.getInt("id_autor");
                regs[i][1]=rs.getString("autor");
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
