package cn.com.skynet.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="forum_user")
public class User
{
    @Id
    private String id;
    private String name;
    private String pwd;
    private String email;
    
    public User(String name, String pwd, String email)
    {
        super();
        this.name = name;
        this.pwd = pwd;
        this.email = email;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPwd()
    {
        return pwd;
    }
    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

}
