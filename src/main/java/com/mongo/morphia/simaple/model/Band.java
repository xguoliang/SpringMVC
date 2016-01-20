package com.mongo.morphia.simaple.model;

import com.google.code.morphia.annotations.*;
import com.google.code.morphia.utils.IndexDirection;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
/**
 * @Id 注释指示 Morphia 哪个字段用作文档 ID。如果您试图持久保存对象（其 @Id 注释的字段为 null），则 Morphia 会为您自动生成 ID 值。
Morphia 试图持久保存每一个它遇到的没有注释的字段，除非它们标有 @Transient 注释。例如，在文档中name 和 genre 属性将被保存为 string，
并具有 name 和 genre 键。
 * @Transient：Morphia 试图持久保存每一个它遇到的没有注释的字段，除非它们标有 @Transient 注释
 * @embedded： 成员对象将被视为嵌入的
 * @Reference： 注释说明对象是对另外一个集合中的文档的引用
 * 所有注解都有以(x)中的X为名，如果没有，则以类名，或者属性名为默认的对象名
 * 
 *morphia注解说明介绍：
Entity("employees")
classEmployee{
  @IdObjectId id;// auto-generated, if not set (see ObjectId)
  String firstName, lastName;// value types are automatically persisted
  Long salary =null;// only non-null values are stored 

  @EmbeddedAddress address;

  @ReferenceEmployee manager;// refs are stored*, and loaded automatically
  @ReferenceList<Employee> underlings;// interfaces are supported

  @SerializedEncryptedReviews;// stored in one binary field 
 
  @Property("started")Date startDate;//fields can be renamed
  @Property("left")Date endDate;

  @Indexedboolean active =false;//fields can be indexed for better performance
  @NotSavedstring readButNotStored;//fields can read, but not saved
  @Transientint notStored;//fields can be ignored (load/save)
  transientboolean stored =true;//not @Transient, will be ignored by Serialization/GWT for example.

  //Lifecycle methods -- Pre/PostLoad, Pre/PostSave...
  @PostLoadvoid postLoad(DBObject dbObj){...}
}
 */
@Entity("bands")
public class Band {

    @Id
    ObjectId id;

    @Indexed(value = IndexDirection.ASC, name = "bandName", unique = true)
    String name;

    @Indexed(value = IndexDirection.ASC, name = "bandGenre")
    String genre;

    @Reference
    Distributor distributor;

    @Reference("catalog")
    List<Song> songs = new ArrayList<Song>();

    @Embedded
    List<String> members = new ArrayList<String>();

    @Embedded("info")
    ContactInfo info;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ContactInfo getInfo() {
        return info;
    }

    public void setInfo(ContactInfo info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Band band = (Band) o;

        if (distributor != null ? !distributor.equals(band.distributor) : band.distributor != null) return false;
        if (genre != null ? !genre.equals(band.genre) : band.genre != null) return false;
        if (id != null ? !id.equals(band.id) : band.id != null) return false;
        if (info != null ? !info.equals(band.info) : band.info != null) return false;
        if (members != null ? !members.equals(band.members) : band.members != null) return false;
        if (name != null ? !name.equals(band.name) : band.name != null) return false;
        if (songs != null ? !songs.equals(band.songs) : band.songs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (distributor != null ? distributor.hashCode() : 0);
        result = 31 * result + (songs != null ? songs.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
