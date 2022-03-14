package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {

    protected static final Logger LOGGER= Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

   // private Class<T> type;
    public String date[][];

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field){
        StringBuilder sb=new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE "+field+" =?");
        return sb.toString();
    }
    private String createSelectAllQ(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(type.getSimpleName());
        return stringBuilder.toString();
    }
    private String createInsertQuery(T t){
        StringBuilder sb=new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES ( ");
        for(int i=0;i<type.getDeclaredFields().length;i++){
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");

        return sb.toString();
    }

    private String createUpdateQuery(String field){
        StringBuilder sb=new StringBuilder();
        sb.append(" UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for(Field f:type.getDeclaredFields()){
            sb.append(f.getName()+"=? , ");
        }
        sb.setLength(sb.length()-2);
        sb.append(" WHERE "+field+"=?");

        return sb.toString();
    }

    private String createDeleteQuery(String field){
        StringBuilder sb=new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE "+field+" =?");

        return sb.toString();
    }

    public List<T> findAll(){

        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        ResultSet resultSet1=null;
        String query=createSelectAllQ();

        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            resultSet1=resultSet;

            date=new String[50][40];
            int j=0;
            while(resultSet.next()){
                for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
                    Object o=resultSet.getObject(i);
                    if(o!=null){
                        date[j][i-1]=o.toString();
                    }else{
                        date[j][i-1]="0";
                    }
                }
                j++;
            }
            date[j+1]=null;
            return createObjects(resultSet1);

        } catch (SQLException exception){
        LOGGER.log(Level.WARNING,type.getName()+"DAO:findAll"+exception.getMessage());
    }finally{

        ConnectionFactory.close(resultSet);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }
        return null;

    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list=new ArrayList<T>();

        try{
            while(resultSet.next()){
                T instance=type.newInstance();
                for(Field field:type.getDeclaredFields()){
                    Object val=resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor=
                            new PropertyDescriptor(field.getName(),type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(instance,val);

                }
                list.add(instance);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;

    }

    public T findById(int id)  {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=createSelectQuery("id");

        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            return createObjects(resultSet).get(0);

        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findbyId"+throwables.getMessage());
        }finally{

            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }

    public int insert(T t){

        Connection connection=null;
        PreparedStatement statement=null;
        int result=0;
        String query=createInsertQuery(t);

        try{
        connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            int i=1;
            for(Field field:t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                value=field.get(t);
                statement.setObject(i,value);
                i++;
            }
            result=statement.executeUpdate();
            return result;
        } catch (SQLException | IllegalAccessException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:Insert"+throwables.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    public T update(T t){

        Connection connection=null;
        PreparedStatement statement=null;
        int result=0;
        String query=createUpdateQuery("id");

        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            int i=1;
            for(Field field:type.getDeclaredFields()){
               PropertyDescriptor pd=new PropertyDescriptor(field.getName(),type);
               Method method=pd.getReadMethod();
               Object obj=method.invoke(t);
               statement.setObject(i,obj);
               i++;

            }
            for(Field field:type.getDeclaredFields()){
                PropertyDescriptor pd=new PropertyDescriptor(field.getName(),type);
                Method method=pd.getReadMethod();
                Object obj=method.invoke(t);
                statement.setObject(i,obj);
                break;
            }
            result=statement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException | IntrospectionException | InvocationTargetException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:Update"+throwables.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }

    public int delete(int id){

        Connection connection=null;
        PreparedStatement statement=null;

        String query=createDeleteQuery("id");

        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
            return 1;
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:Delete"+throwables.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;


    }




}
