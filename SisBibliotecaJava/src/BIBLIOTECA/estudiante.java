
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class estudiante {
    private int id_estudiante;
    private String dni;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String grado;
    private String seccion;
    private int id_nivel;
    conexion_biblioteca cb;
    
    estudiante(int id_d,String dnii,String nom,String ape,String dir,String gra,String sec,int id_n)
    {   id_estudiante=id_d;
        dni=dnii;
        nombres=nom;
        apellidos=ape;
        direccion=dir;
        grado=gra;
        seccion=sec;
        id_nivel=id_n;
        this.cb=new conexion_biblioteca();
    }
    
    public int guardar()
    {   int error=0;
        int id_d=0;
        String sql="INSERT INTO estudiante VALUES(0,'"+dni+"','"+nombres+"','"+apellidos+"','"+direccion+"','"+grado+"','"+seccion+"','"+id_nivel+"')";
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
        return id_estudiante;
    }
    
    void actualizar()
    {   int error=0;
        String sql="UPDATE estudiante SET dni='"+dni+"',nombres='"+nombres+"',apellidos='"+apellidos+"',direccion='"+direccion+"',grado='"+grado+"',seccion='"+seccion+"',id_nivel='"+id_nivel+"' WHERE id_estudiante='"+id_estudiante+"'";
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
        String sql="DELETE FROM estudiante WHERE id_estudiante='"+id_estudiante+"'";
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
        String sql="SELECT * FROM estudiante WHERE dni='"+dni+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            if(rs.next())
            {   regs[0][0]=rs.getInt("id_estudiante");
                regs[0][1]=rs.getString("dni");
                regs[0][2]=rs.getString("nombres");
                regs[0][3]=rs.getString("apellidos");
                regs[0][4]=rs.getString("direccion");
                regs[0][5]=rs.getString("grado");
                regs[0][6]=rs.getString("seccion");
                regs[0][7]=rs.getInt("id_nivel");
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
        String sql="SELECT * FROM estudiante WHERE "+campo+" "+operador+"'"+valor+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int c=0;
            while(rs.next())
            {   regs[c][0]=rs.getInt("id_estudiante");
                regs[c][1]=rs.getString("dni");
                regs[c][2]=rs.getString("nombres");
                regs[c][3]=rs.getString("apellidos");
                regs[c][4]=rs.getString("direccion");
                regs[c][5]=rs.getString("grado");
                regs[c][6]=rs.getString("seccion");
                regs[c][7]=rs.getInt("id_nivel");
                c++;
            }
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
        return regs;
    }
}
