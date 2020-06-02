
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class libro {
    private int id_libro;
    private String codigo;
    private String titulo;
    private int id_autor;
    private int id_editorial;
    private int id_categoria;
    private int cantidad;
    private int disponible;
    conexion_biblioteca cb;
    
    libro(int id_l,String cod,String ti,int id_a,int id_e,int id_c,int c,int d)
    {   id_libro=id_l;
        codigo=cod;
        titulo=ti;
        id_autor=id_a;
        id_editorial=id_e;
        id_categoria=id_c;
        cantidad=c;
        disponible=d;
        this.cb=new conexion_biblioteca();
    }
    
    public int guardar()
    {   int error=0;
        int id_l=0;
        String sql="INSERT INTO libro VALUES(0,'"+codigo+"','"+titulo+"','"+id_autor+"','"+id_editorial+"','"+id_categoria+"','"+cantidad+"','"+disponible+"')";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            cb.desconectar();
        }
        
        catch(SQLException e)
        {   error=1;
            System.out.println(e);
        }
        if(error==0)
            JOptionPane.showMessageDialog(null, "Registro guardado exitosamente...");
        else
            JOptionPane.showMessageDialog(null, "Revisar datos correctos en los campos...","ERROR: REGISTRO NO GUARDADO",0);
        return id_libro;
    }
    
    void actualizar()
    {   int error=0;
        String sql="UPDATE libro SET codigo='"+codigo+"',titulo='"+titulo+"',id_autor='"+id_autor+"',id_editorial='"+id_editorial+"',id_categoria='"+id_categoria+"',cantidad='"+cantidad+"',disponible='"+disponible+"' WHERE id_libro='"+id_libro+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            cb.desconectar();
        }
        catch(SQLException e)
        {   error=1;
            System.out.println(e);
        }
        if(error==0)
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente...");
        else
            JOptionPane.showMessageDialog(null, "Revisar datos correctos en los campos...","ERROR: REGISTRO NO MODIFICADO",0);
    }
    void eliminar()
    {   int error=0;
        String sql="DELETE FROM libro WHERE id_libro='"+id_libro+"'";
        try
        {   PreparedStatement pstm=cb.getConnection().prepareStatement(sql);
            pstm.executeUpdate();
            cb.desconectar();
        }
        catch(SQLException e)
        {   error=1;
            System.out.println(e);
        }
        if(error==0)
            JOptionPane.showMessageDialog(null, "Registro ELIMINADO exitosamente...");
        else
            JOptionPane.showMessageDialog(null, "No se pudo eliminar registro...","ERROR: REGISTRO NO ELIMINADO",0);
    }
    Object[][] buscarXdni()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM libro WHERE codigo='"+codigo+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            if(rs.next())
            {   regs[0][0]=rs.getInt("id_libro");
                regs[0][1]=rs.getString("codigo");
                regs[0][2]=rs.getString("titulo");
                regs[0][3]=rs.getInt("id_autor");
                regs[0][4]=rs.getInt("id_editorial");
                regs[0][5]=rs.getInt("id_categoria");
                regs[0][6]=rs.getInt("cantidad");
                regs[0][7]=rs.getInt("disponible");
            }
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
        return regs;
    }
    Object[][] buscar(String campo,String operador,String valor)
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        if(operador.equals("LIKE"))
            valor="%"+valor+"%";
        String sql="SELECT * FROM libro WHERE "+campo+" "+operador+"'"+valor+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int c=0;
            while(rs.next())
            {   regs[c][0]=rs.getInt("id_libro");
                regs[c][1]=rs.getString("codigo");
                regs[c][2]=rs.getString("titulo");
                regs[c][3]=rs.getInt("id_autor");
                regs[c][4]=rs.getInt("id_editorial");
                regs[c][5]=rs.getInt("id_categoria");
                regs[c][6]=rs.getInt("cantidad");
                regs[c][7]=rs.getInt("disponible");
                c++;
            }
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
        return regs;
    }
}
