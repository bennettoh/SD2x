import java.util.Date;

public abstract class Document {
    private String title;
    private String author;
    private Date date;
    private PublishingLocation publoc;

    public Document(String title, String author, Date date, String city, String state, String postCode) {
	this.title = title;
	this.author = author;
	this.date = date;
	this.publoc = new PublishingLocation(city, state, postCode);
    }
    
    public String getTitle() {
	return title;
    }

    public String getAuthor() {
	return author;
    }
    
    public Date getDate() {
	return date;
    }

    public String getCity() {
	return publoc.getCity();
    }

    public String getState() {
	return publoc.getState();
    }

    public String getPostCode() {
	return publoc.getPostCode();
    }

    public int compareWithGeneralDate(Date date){
	return date.compareTo(date);
    }
    
    public boolean sameAuthor(Document article){
	return author.equals(article.getAuthor());
    }

    public int compareDates(Document article){
	return date.compareTo(article.getDate());
    }
}
