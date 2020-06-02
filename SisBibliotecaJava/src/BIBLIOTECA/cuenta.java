
package BIBLIOTECA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class cuenta {
    private int id_cuenta;
    private int id_cargo;
    private String nombres;
    private String apellidos;
    private String login;
    private String contraseña;
    conexion_biblioteca cb;
    
    cuenta(int id_cu,int id_ca,String nom,String ape,String lo, String con)
    {   id_cuenta=id_cu;
        id_cargo=id_ca;
        nombres=nom;
        apellidos=ape;
        login=lo;
        contraseña=con;
        
        this.cb=new conexion_biblioteca();
    }
    
    public int guardar()
    {   int error=0;
        int id_cu=0;
        String sql="INSERT INTO cuenta VALUES(0,'"+id_cargo+"','"+nombres+"','"+apellidos+"','"+login+"','"+contraseña+"')";
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
        return id_cuenta;
    }
    
    void actualizar()
    {   int error=0;
        String sql="UPDATE cuenta SET id_cuenta='"+id_cuenta+"',nombres='"+nombres+"',apellidos='"+apellidos+"',login='"+login+"',contraseña='"+contraseña+"' WHERE id_cuenta='"+id_cuenta+"'";
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
        String sql="DELETE FROM cuenta WHERE id_docente='"+id_cuenta+"'";
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
    Object[][] buscarXapellidos()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM cuenta WHERE apellidos='"+apellidos+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            if(rs.next())
            {   regs[0][0]=rs.getInt("id_cuenta");
                regs[0][1]=rs.getString("id_cargo");
                regs[0][2]=rs.getString("nombres");
                regs[0][3]=rs.getString("apellidos");
                regs[0][4]=rs.getString("login");
                regs[0][5]=rs.getInt("contraseña");
            }
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
        return regs;
    }
    Object[][] buscarXcuenta()
    {   Object[][] regs=new Object[10][8];
        regs[0][0]=0;
        String sql="SELECT * FROM cuenta WHERE login='"+login+"'and contraseña='"+contraseña+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            if(rs.next())
            {   regs[0][0]=rs.getInt("id_cuenta");
                regs[0][1]=rs.getString("id_cargo");
                regs[0][2]=rs.getString("nombres");
                regs[0][3]=rs.getString("apellidos");
                regs[0][4]=rs.getString("login");
                regs[0][5]=rs.getInt("contraseña");
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
        String sql="SELECT * FROM cuenta WHERE "+campo+" "+operador+"'"+valor+"'";
        try
        {   PreparedStatement pstm = cb.getConnection().prepareStatement(sql);
            ResultSet rs=pstm.executeQuery();
            int c=0;
            while(rs.next())
            {   regs[c][0]=rs.getInt("id_cuenta");
                regs[c][1]=rs.getString("id_cargo");
                regs[c][2]=rs.getString("nombres");
                regs[c][3]=rs.getString("apellidos");
                regs[c][4]=rs.getString("login");
                regs[c][5]=rs.getInt("contraseña");
                c++;
            }
        }
        catch(SQLException e)
        {   System.out.println(e);
        }
        return regs;
    }
}
