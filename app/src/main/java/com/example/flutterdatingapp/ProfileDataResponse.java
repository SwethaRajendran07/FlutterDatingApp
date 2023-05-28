package com.example.flutterdatingapp;

import java.util.ArrayList;
import java.util.List;

public class ProfileDataResponse {
    Invites InvitesObject;
    Likes LikesObject;


    // Getter Methods

    public Invites getInvites() {
        return InvitesObject;
    }

    public Likes getLikes() {
        return LikesObject;
    }

    // Setter Methods

    public void setInvites(Invites invitesObject) {
        this.InvitesObject = invitesObject;
    }

    public void setLikes(Likes likesObject) {
        this.LikesObject = likesObject;
    }
}
class Likes {
    private List<Profile> profiles;

    private boolean can_see_profile;
    private float likes_received_count;


    public List<Profile> getProfiles() {
        return profiles;
    }
    // Getter Methods

    public boolean getCan_see_profile() {
        return can_see_profile;
    }

    public float getLikes_received_count() {
        return likes_received_count;
    }

    // Setter Methods

    public void setCan_see_profile(boolean can_see_profile) {
        this.can_see_profile = can_see_profile;
    }

    public void setLikes_received_count(float likes_received_count) {
        this.likes_received_count = likes_received_count;
    }
}
class Profile {
    private String first_name;
    private String avatar;

    public String getFirst_name() {
        return first_name;
    }

    public String getAvatar() {
        return avatar;
    }
}
class Invites {
    ArrayList< Object > profiles = new ArrayList < Object > ();
    private float totalPages;
    private float pending_invitations_count;


    // Getter Methods

    public float getTotalPages() {
        return totalPages;
    }

    public float getPending_invitations_count() {
        return pending_invitations_count;
    }

    // Setter Methods

    public void setTotalPages(float totalPages) {
        this.totalPages = totalPages;
    }

    public void setPending_invitations_count(float pending_invitations_count) {
        this.pending_invitations_count = pending_invitations_count;
    }
}


