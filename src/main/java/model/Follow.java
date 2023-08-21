package model;

public class Follow {
    private int followerId;
    private int followingId;

    public Follow(int followerId, int followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public int getFollowerId() {
        return followerId;
    }


    public int getFollowingId() {
        return followingId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followerId=" + followerId +
                ", followingId=" + followingId +
                '}';
    }
}