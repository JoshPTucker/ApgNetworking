package ApgNetworking.models;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class ApgPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String link;
    @Size(min=0, max=140)
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apguser_id")
    private ApgUser apguser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ApgUser getApguser() {
        return apguser;
    }

    public void setApguser(ApgUser apguser) {
        this.apguser = apguser;
    }
}
