
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class p_docente {
    private int id_p_docente;
    private int id_docente;
    private int id_libro;
    private int cantidad;
    private String fecha_prestamo;
    private int id_estado;
    private String fecha_entrega;
    conexion_biblioteca cb;
    
    p_docente(int id_p_d,int id_d,int id_l,int can,String f_p,int id_e,String f_e)
    {   id_p_docente=id_p_d;
        id_docente=id_d;
        id_libro=id_l;
        cantidad=can;
        fecha_prestamo=f_p;
        id_estado=id_e;
        fecha_entrega=f_e;
        this.cb=new conexion_biblioteca();
    }
    
    public int guardar()
    {   int error=0;
        int id_d_p=0;
        String sql="INSERT INTO prestamo_docente VALUES(0,'"+id_docente+"','"+id_libro+"','"+cantidad+"','"+fecha_prestamo+"','"+id_estado+"','"+fecha_entrega+"')";
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
        return id_p_docente;
    }
    
    void actualizar()
    {   int error=0;
        String sql="UPDATE prestamo_docente SET id_docente='"+id_docente+"',id_libro='"+id_libro+"',cantidad='"+cantidad+"',fecha_prestamo='"+fecha_prestamo+"',id_estado='"+id_estado+"',fecha_entrega='"+fecha_entrega+"' WHERE id_p_docente='"+id_p_docente+"'";
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
        String sql="DELETE FROM prestamo_docente WHERE id_p_docente='"+id_p_docente+"'";
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
    
}
