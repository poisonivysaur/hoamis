
package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

import java.util.*;
import model.User;
import java.io.PrintWriter;


/**
 *
 * A <b>Group</b> object that contains the different attributes of a group and its functions on the system.
 * this is a DAO object of a User Group.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for groups.
 * 
 * @author Leebet-PC
 * @version 1.0 
 * @since 10/29/2017
 */
public class Group {

    /**
     *
     */
    protected String groupname;

    /**
     *
     */
    protected int group_id;

    /**
     *
     */
    protected int creator_id;

    /**
     *
     */
    protected int privacy_set;
    /**
     * 
     * @return 
     */
    public int getPrivacy_set() {
        return privacy_set;
    }

    /**
     *
     * @return
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     *
     * @return
     */
    public int getGroup_id() {
        return group_id;
    }

    /**
     *
     * @return
     */
    public int getCreator_id() {
        return creator_id;
    }
    
    /**
     * Overloaded constructor to be able to be  <b> instantiated and called </b> by other objects without parameters.
     */
    public Group() {
    }
    /**
     * Overloaded constructor to serve as <b> setters</b>
     * 
     * @param groupname - group's name
     */
    public Group(String groupname) {
        //super constructor!
        this.groupname = groupname;
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
        PreparedStatement st = con.prepareStatement("select * from TestForumDB.group where groupname=?"); 
        st.setString(1, groupname);

        ResultSet rs = st.executeQuery();
        while(rs.next()){
            this.group_id = rs.getInt("group_id");
            this.creator_id = rs.getInt("creator_id");
            this.privacy_set = rs.getInt("privacy_set");
        }
    }catch(Exception E){
        E.printStackTrace();
    }
        
    }
    /**
     * Gets result set of users from the database who are not part of a given group.
     * @param groupid - selected group.
     * @return ResultSet of users
     */
    public static ResultSet getNotMembers (int groupid){
    ResultSet as= null;
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
        PreparedStatement st = con.prepareStatement("select a.* from TestForumDB.user a join TestForumDB.membership b on a.user_id = b.user_user_id " +
"where a.user_id NOT IN (select c.user_id from TestForumDB.user c join TestForumDB.membership d on c.user_id = d.user_user_id where d.group_group_id=?) " +
"group by a.user_id;"); 
        st.setInt(1, groupid);

        ResultSet rs = st.executeQuery();

        return rs;


    }catch(Exception E){
        E.printStackTrace();
    }
    
    return as;
    }
    /**
     * Gets result set of users from the database who are part of a given group.
     * @param groupid - selected group.
     * @return result set of members
     */
    public static ResultSet getMembers (int groupid){
    ResultSet as= null;
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
        PreparedStatement st = con.prepareStatement("select * from TestForumDB.user a join TestForumDB.membership b on a.user_id = b.user_user_id where b.group_group_id=?"); 
        st.setInt(1, groupid);

        ResultSet rs = st.executeQuery();

        return rs;


    }catch(Exception E){
        E.printStackTrace();
    }
    return as;
    } 
    /**
     * checks if there is a duplicated group name existing to prevent database exception errors.
     * @param groupname - group's name
     * @return boolean
     */
    public static boolean checkDuplicateName(String groupname){
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
        PreparedStatement st = con.prepareStatement("select * from TestForumDB.group where groupname=?" ); 
        st.setString(1, groupname);

        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            return true;
        }

        }
        catch(Exception E){
            E.printStackTrace();
        }
        return false;
    }
    /**
     * Gets result set of groups from the database who are public or the user is part of.
     * @param user_id - selected user identification number
     * @return 
     */
    public static ResultSet getAllGroups(int user_id){
        ResultSet as= null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("select DISTINCT a.* from TestForumDB.group a left join TestForumDB.membership b on a.group_id = b.group_group_id where a.privacy_set = 1 or b.user_user_id = ? group by 1,2,3;"); 
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
             
            return rs;
  
        }catch(Exception E){
            E.printStackTrace();
        }
        return as;
    }
    /**
     * Gets result set of user's groups from the database.
     * @param username - user's username
     * @return 
     */
    public static ResultSet getUsersGroups(String username){
        ResultSet as= null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("select * from TestForumDB.group a join TestForumDB.membership b on a.group_id = b.group_group_id where b.user_user_id=?;"); 
            //st.setInt(1, getUserID(username));

            ResultSet rs = st.executeQuery();
             
            return rs;  

        }catch(Exception E){
            E.printStackTrace();
        }
        return as;
    }
    /**
     * Gets the number of members of a given group including the user.
     * @param group_id - group's identification number
     * @return int
     */
    public static int getNumOfMembers(int group_id){
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
        PreparedStatement st = con.prepareStatement("select * from membership where group_group_id=?"); 
        st.setInt(1, group_id);

        ResultSet rs = st.executeQuery();
        int ctr=0;
        while(rs.next()){
            ctr++;
        }
        return ctr;

    }catch(Exception E){
        E.printStackTrace();
    }
    return 0;
    }
    /**
     * Checks if selected user is part of the selected group group.
     * @param user_id
     * @param group_id
     * @return boolean
     */
    public static boolean isAMember(int user_id, int group_id){
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
        PreparedStatement st = con.prepareStatement("select * from membership where group_group_id=? and user_user_id=?" ); 
        st.setInt(1, group_id);
        st.setInt(2, user_id);

        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            return true;
        }

    }catch(Exception E){
        E.printStackTrace();
    }
        
        return false;
    }
    /**
     * Adding a new group to the database and also adding the selected members and member groups
     * @param groupname - group's name
     * @param username - creator's username
     * @param members - array of members to be added to new group
     * @param groups - array of groups to be added to the new group
     * @param settings - privacy settings of the new group
     * @return 
     */
    public static boolean AddGroup(String groupname, String username, String[] members, String[] groups,int settings){
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("INSERT INTO TestForumDB.group(group_id,groupname, creator_id,privacy_set) VALUES (NULL,?,?,?);"); 
            st.setString(1, groupname);
            //st.setInt(2, getUserID(username));
            st.setInt(3, settings);
           
            st.executeUpdate();
            ResultSet rst = st.getGeneratedKeys();
            int last_inserted_id= 0;
            if(rst.next())
            {
                 last_inserted_id = rst.getInt(1);
            }
            int group_id = last_inserted_id;
           
            PreparedStatement stt = con.prepareStatement("INSERT INTO TestForumDB.membership(user_user_id, group_group_id,membership_type) VALUES (?,?,1);"); 
           
            //stt.setInt(1, getUserID(username));
            stt.setInt(2, group_id);
            stt.executeUpdate();
            
            PreparedStatement sttt = con.prepareStatement("INSERT INTO TestForumDB.membership(user_user_id, group_group_id,membership_type) VALUES (?,?,1);"); 
            if(members!=null){
                for (String member_name : members) {

                    //sttt.setInt(1, getUserID(member_name));
                    sttt.setInt(2, group_id);
                    sttt.executeUpdate();
                }
            }
            if(groups!=null){
                for (String group_name : groups){
                    Group g = new Group(group_name);
                    System.out.println("HOOooooooooy ptuangina nyo");
                    ResultSet group_members = g.getMembers(g.getGroup_id());
                    while(group_members.next()){
                        sttt.setInt(1, group_members.getInt("user_id"));
                        sttt.setInt(2, group_id);
                        if(!(g.isAMember(group_members.getInt("user_id"),group_id)))
                            sttt.executeUpdate();
                    }
                }
            }
            
            return true;

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    }
    /**
     * Adds invited members to the group
     * @param group_id group's identification number
     * @param members array of members to be added
     * @return 
     */
    public static boolean AddMembers(int group_id, String[] members){
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            
            
           
            PreparedStatement sttt = con.prepareStatement("INSERT INTO TestForumDB.membership(user_user_id, group_group_id,membership_type) VALUES (?,?,1);"); 
           
            for (String one : members) {
                
                //sttt.setInt(1, getUserID(one));
                sttt.setInt(2, group_id);
                sttt.executeUpdate();
            }
            return true;

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    }
}
