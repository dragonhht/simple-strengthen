package simple.strengthen.bean;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-23
 */
public interface StudentInterface {
    void print();

    void print(int age, String address);

    String getAddress();

    int getId();

    String getName();

    void setId(int id);

    void setName(String name);
}
