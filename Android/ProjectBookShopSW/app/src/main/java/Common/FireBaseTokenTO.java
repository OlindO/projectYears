package Common;

import java.util.Date;

/**
 * Created by yanis on 18/04/2017.
 */

public class FireBaseTokenTO {
    private long id;
    private String token;
    private Date firstConnection;
    private Date lastConnection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFirstConnection() {
        return firstConnection;
    }

    public void setFirstConnection(Date firstConnection) {
        this.firstConnection = firstConnection;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }
}
