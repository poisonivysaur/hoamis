package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import model.dao.User;
import java.util.*;
import java.io.PrintWriter;

/**
 *
 * A <b>User Group</b> object that contains the different attributes of a group and
 * its functions on the system. this is a DAO object of a User Group.
 *
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database
 * object for groups.
 *
 * @author Leebet Barraquias
 * @version 1.0
 * @since 10/29/2017
 * 
 * original code: 10/27/17 by Leebet Barraquias
 * last update: 11/07/17 by L. Barraquias - added getters & setters
 */
public class Usergroup {

    /**
     *
     */
    protected String groupname;

    /**
     *
     */
    protected int userGroupID;

    /**
     *
     */
    protected String description;

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
    public int getUserGroupID() {
        return userGroupID;
    }

    public String getDescription() {
        return description;
    }
    
   

    /**
     * Overloaded constructor to be able to be  <b> instantiated and called </b>
     * by other objects without parameters.
     */
    public Usergroup() {
    }

    /**
     * Overloaded constructor to serve as <b> setters</b>
     *
     * @param groupname - group's name
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public Usergroup(String groupname) {
        //super constructor! straight fram da DB
        this.groupname = groupname;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from hoamis.USERGROUPS where groupname=?");
            st.setString(1, groupname);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                this.userGroupID = rs.getInt("group_id");
                this.description = rs.getString("description");
                this.privacy_set = rs.getInt("privacy_set");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    /**
     * Gets result set of users from the database who are not part of a given
     * group.
     *
     * @param groupid - selected group.
     * @return ResultSet of users
     * 
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public static ResultSet getNotMembers(int groupid) {
        ResultSet as = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select a.* from hoamis.users a join hoamis.usergroupmembers b on a.userID = b.userID "
                    + "where a.userID NOT IN (select c.userID from hoamis.users c join hoamis.usergroupmembers d on c.userID = d.userID where d.userGroupID=?) "
                    + "group by a.userID;");
            st.setInt(1, groupid);

            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }

        return as;
    }

    /**
     * Gets result set of users from the database who are part of a given group.
     *
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     * @param groupid - selected group.
     * @return result set of members
     */
    public static ResultSet getMembers(int groupid) {
        ResultSet as = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from hoamis.user a join hoamis.usergroupmembers b on a.user_id = b.userID where b.userGroupID=?");
            st.setInt(1, groupid);

            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }

    /**
     * checks if there is a duplicated group name existing to prevent database
     * exception errors.
     *
     * @param groupname - group's name
     * @return boolean
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public static boolean checkDuplicateName(String groupname) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from hoamis.group where groupname=?");
            st.setString(1, groupname);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }

    /**
     * Gets result set of groups from the database who are public or the user is
     * a member of.
     *
     * @param user_id - selected user identification number
     * @return
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public static ResultSet getAllGroups(int user_id) {
        ResultSet as = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select DISTINCT a.* from hoamis.group a left join hoamis.usergroupmembers b on a.group_id = b.userGroupID where a.privacy_set = 1 or b.userID = ? group by 1,2,3;");
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }

    /**
     * Gets result set of user's groups from the database.
     *
     * @param userID - user's username
     * @return
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public static ResultSet getUsersGroups(int userID) {
        ResultSet as = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from hoamis.usergroups a join hoamis.usergroupmembers b on a.userGroupID = b.userGroupID where b.userID=?;");
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }

    /**
     * Gets the number of members of a given group including the user.
     *
     * @param group_id - group's identification number
     * @return int
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public static int getNumOfMembers(int group_id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from usergroupmembers where userGroupID=?");
            st.setInt(1, group_id);

            ResultSet rs = st.executeQuery();
            int ctr = 0;
            while (rs.next()) {
                ctr++;
            }
            return ctr;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return 0;
    }

    /**
     * Checks if selected user is part of the selected group group.
     *
     * @param user_id
     * @param group_id
     * @return boolean
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB
     */
    public static boolean isAMember(int user_id, int group_id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from usergroupmembers where userGroupID=? and userID=?");
            st.setInt(1, group_id);
            st.setInt(2, user_id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception E) {
            E.printStackTrace();
        }

        return false;
    }

    /**
     * Adding a new group to the database and also adding the selected members
     * and member groups
     *
     * @param groupname - group's name
     * @param userID - creator's ID
     * @param members - array of user ids of members to be added to new group
     * @param groups - array of groups to be added to the new group
     * @param settings - privacy settings of the new group
     * @return
     * 
     * last update: 11/07/17 by L. Barraquias - updated query code for hoamis DB | Change username to userID
     */
    public static boolean AddGroup(String groupname, int userID, int[] members, String[] groups, int settings) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("INSERT INTO hoamis.usergroups(userGroupID,description,privacy_set,groupname) VALUES (NULL,'ah qqo',?,?);");
            st.setString(1, groupname);
            st.setInt(2, settings);

            st.executeUpdate();
            ResultSet rst = st.getGeneratedKeys();
            int last_inserted_id = 0;
            if (rst.next()) {
                last_inserted_id = rst.getInt(1);
            }
            int group_id = last_inserted_id;

            PreparedStatement stt = con.prepareStatement("INSERT INTO hoamis.usergroupmembers(userID, userGroupID) VALUES (?,?);");

            stt.setInt(1, userID);
            stt.setInt(2, group_id);
            stt.executeUpdate();

            PreparedStatement sttt = con.prepareStatement("INSERT INTO hoamis.usergroupmembers(userID, userGroupID) VALUES (?,?);");
            if (members != null) {
                for (int member_id : members) {

                    sttt.setInt(1, member_id);
                    sttt.setInt(2, group_id);
                    sttt.executeUpdate();
                }
            }
            if (groups != null) {
                for (String group_name : groups) {
                    Usergroup g = new Usergroup(group_name);
                    System.out.println("HOOooooooooy ptuangina nyo");
                    ResultSet group_members = g.getMembers(g.getUserGroupID());
                    while (group_members.next()) {
                        sttt.setInt(1, group_members.getInt("userID"));
                        sttt.setInt(2, group_id);
                        if (!(g.isAMember(group_members.getInt("userID"), group_id))) {
                            sttt.executeUpdate();
                        }
                    }
                }
            }
            return true;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }

    /**
     * Adds invited members to the group
     *
     * @param group_id group's identification number
     * @param members array of members to be added
     * @return
     */
    public static boolean AddMembers(int group_id, int[] members) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");

            PreparedStatement sttt = con.prepareStatement("INSERT INTO hoamis.usergroupmembers(userID, userGroupID) VALUES (?,?);");

            // TO-DO Array of MemberID instead of names
            for (int member_id : members) {

                sttt.setInt(1, member_id);
                sttt.setInt(2, group_id);
                sttt.executeUpdate();
            }
            return true;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }
}
