package cn.com.skynet.database.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="papers")
public class Paper
{
    @Id
    private String id;
    private String title;
    private String author;
    private String content;
    private Date createTime;
    private Date lastModifyTime;
    
    public Paper(String id, String title, String author, String content, Date createTime, Date lastModifyTime)
    {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
    }
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public Date getLastModifyTime()
    {
        return lastModifyTime;
    }
    public void setLastModifyTime(Date lastModifyTime)
    {
        this.lastModifyTime = lastModifyTime;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.author);
        sb.append(" post a forum, title is ");
        sb.append(this.title);
        sb.append(" at ");
        sb.append(this.createTime);
        sb.append(", last modify time is ");
        sb.append(this.lastModifyTime);
        sb.append(", content size is ");
        sb.append(this.content.length());
        return sb.toString();
    }
}
