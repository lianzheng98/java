package com.pg.cc.sb.core.zk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Comparator;

public class ZkVote {
    //当前服务器状态
    public enum ServerState {
        LOOKING,    //观察
        FOLLOWING,  //跟随
        LEADING,    //领导者
        OBSERVING   //观察者
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vote {
        //个人集群id
        long sid;
        //事务id
        long zxid;
        //选举epoch，一个选举周期中
        long electionEpoch;
        private static final Comparator<Vote> comparator = Comparator.comparingLong(Vote::getElectionEpoch)
                .thenComparingLong(Vote::getZxid)
                .thenComparingLong(Vote::getSid);
    }

    @Data
    public static class FastLeaderElection {
        public static boolean checkVote(Vote v1, Vote v2) {
            return Vote.comparator.compare(v1, v2) > 0;
        }

    }

    @Test
    public void testVoteComparator() {
        // start select leader
        /**
         * 第一轮
         *  1 1,0
         *  2 2,0
         * 第二轮
         *  1 2,0
         *  2 2,0
         *
         */
        Vote vote1 = new Vote();
        vote1.setSid(1);
        vote1.setZxid(2);
        vote1.setElectionEpoch(1);

        Vote vote2 = new Vote();
        vote2.setSid(2L);
        vote2.setZxid(2L);
        vote2.setElectionEpoch(1L);
        System.out.println(FastLeaderElection.checkVote(vote1, vote2));
    }
}
