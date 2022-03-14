package bll.validators;

import dao.AbstractDAO;


import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("rawtypes")
public abstract class AbstractBLL<H,T,D extends AbstractDAO> {

    protected static final Logger LOGGER=Logger.getLogger(AbstractBLL.class.getName());

    private Class<T> type;
    private  Class<D> typeDAO;
    private D classDAO;
    private List<Validator<T>> validators;

    @SuppressWarnings("unchecked")
    public AbstractBLL() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.typeDAO = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];

        try {
            classDAO = typeDAO.newInstance();
            validators = getValidators();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
     catch(SecurityException e){
         e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }

    public boolean validateAll(T p){
        for(Validator<T> validator:validators){
            if(validator.validate((T) p)==false){
                return false;
            }
        }
            return true;
    }




    public int insert(T t){
        if(validateAll(t)==false){
            return 0;
        }
        int result= (int) classDAO.insert(t);
        return result;
    }

    public int delete(int id){

        int result= classDAO.delete(id);

        if(result==0){
            throw new NoSuchElementException("Nu exista acest element");
        }
        return result;
    }



    protected abstract List<Validator<T>> getValidators();
}
