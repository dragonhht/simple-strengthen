package simple.strengthen.bean;

import lombok.Data;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-3-23
 */
@Data
public class Student implements StudentInterface {

    private int id = 33;

    private String name = "张三";


    @Override
    public void print() {
        System.out.println("id: " + id + ", name: " + name);
    }

    @Override
    public void print(int age, String address) {
        System.out.println("id: " + id + ", name: " + name + ", age: " + age + ", address: " + address);
    }

    @Override
    public String getAddress() {
        return "南昌";
    }

}
