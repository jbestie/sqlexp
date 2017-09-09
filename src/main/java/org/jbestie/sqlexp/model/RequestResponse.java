package org.jbestie.sqlexp.model;

public class RequestResponse {
    private boolean correct;
    private String message;
    private QueryResult queryResult;
    
    public RequestResponse(boolean correct, String message, QueryResult queryResult) {
        this.correct = correct;
        this.message = message;
        this.queryResult = queryResult;
    }
    
    public boolean isCorrect() {
        return correct;
    }
    
    public String getMessage() {
        return message;
    }
    
    public QueryResult getQueryResult() {
        return queryResult;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (correct ? 1231 : 1237);
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((queryResult == null) ? 0 : queryResult.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestResponse other = (RequestResponse) obj;
        if (correct != other.correct)
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (queryResult == null) {
            if (other.queryResult != null)
                return false;
        } else if (!queryResult.equals(other.queryResult))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "RequestResponse [correct=" + correct + ", message=" + message + ", queryResult=" + queryResult + "]";
    }
}
