package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DongDexuan
 * @date 2018-6-20 15:18
 * @desc
 */
@Data
@NoArgsConstructor
public class TestGsonFormat {

    /**
     * SessionParams : {"os":"Linux","wake_id":"253513e04a441f72459235331e70e","dtype":"audio","dev_lang":"cpp","interact_mode":"continuous","stmid":"audio-2","dsrc":"user","scene":"main","sid":"cida15e427c@dx00990e7e8ba3010002","ver_type":"desktop_pc","sample_rate":"16000","sdk_ver":"5.5.1026.0000","scity":"ch","prot_type":"pb"}
     * Msg : {"Type":"text","ContentType":"json","Content":{"intent":{"semantic":{"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}},"dialog_stat":"dataInvalid","demand_semantic":{"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"},"save_history":true,"uuid":"atn034d4688@dx00070e7e8ba5a10e01","engine_time":12.645,"sid":"cida15e427c@dx00990e7e8ba3010002","rc":0,"score":1,"used_state":{"state_key":"fg::airCleaner_smartHome::default::default","state":"default"},"answer":{"text":"已为您将空气净化器风速设置为\"低速\""},"orig_semantic":{"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}},"service":"airCleaner_smartHome","search_semantic":{"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"},"state":{"fg::airCleaner_smartHome::default::default":{"state":"default"}},"text":"最小风","array_index":0,"operation":"SET","cid":"cida15e427c@dx00990e7e8163000000"}}}
     * AppId : 5b03ca9b
     * UserId : d9406328824
     * CreateTime : 1529036709
     * UserParams : {"SN_MideaFan":"0000FA111AMS125H0186151100010000"}
     * FromSub : kc
     * MsgId : cida15e427c@dx00990e7e8ba30100021
     */

    private SessionParamsBean SessionParams;
    private MsgBean Msg;
    private String AppId;
    private String UserId;
    private int CreateTime;
    private UserParamsBean UserParams;
    private String FromSub;
    private String MsgId;

    @NoArgsConstructor
    @Data
    public static class SessionParamsBean {
        /**
         * os : Linux
         * wake_id : 253513e04a441f72459235331e70e
         * dtype : audio
         * dev_lang : cpp
         * interact_mode : continuous
         * stmid : audio-2
         * dsrc : user
         * scene : main
         * sid : cida15e427c@dx00990e7e8ba3010002
         * ver_type : desktop_pc
         * sample_rate : 16000
         * sdk_ver : 5.5.1026.0000
         * scity : ch
         * prot_type : pb
         */

        private String os;
        private String wakeId;
        private String dtype;
        private String devLang;
        private String interactMode;
        private String stmid;
        private String dsrc;
        private String scene;
        private String sid;
        private String verType;
        private String sampleRate;
        private String sdkVer;
        private String scity;
        private String protType;
    }

    @NoArgsConstructor
    @Data
    public static class MsgBean {
        /**
         * Type : text
         * ContentType : json
         * Content : {"intent":{"semantic":{"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}},"dialog_stat":"dataInvalid","demand_semantic":{"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"},"save_history":true,"uuid":"atn034d4688@dx00070e7e8ba5a10e01","engine_time":12.645,"sid":"cida15e427c@dx00990e7e8ba3010002","rc":0,"score":1,"used_state":{"state_key":"fg::airCleaner_smartHome::default::default","state":"default"},"answer":{"text":"已为您将空气净化器风速设置为\"低速\""},"orig_semantic":{"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}},"service":"airCleaner_smartHome","search_semantic":{"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"},"state":{"fg::airCleaner_smartHome::default::default":{"state":"default"}},"text":"最小风","array_index":0,"operation":"SET","cid":"cida15e427c@dx00990e7e8163000000"}}
         */

        private String Type;
        private String ContentType;
        private ContentBean Content;

        @NoArgsConstructor
        @Data
        public static class ContentBean {
            /**
             * intent : {"semantic":{"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}},"dialog_stat":"dataInvalid","demand_semantic":{"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"},"save_history":true,"uuid":"atn034d4688@dx00070e7e8ba5a10e01","engine_time":12.645,"sid":"cida15e427c@dx00990e7e8ba3010002","rc":0,"score":1,"used_state":{"state_key":"fg::airCleaner_smartHome::default::default","state":"default"},"answer":{"text":"已为您将空气净化器风速设置为\"低速\""},"orig_semantic":{"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}},"service":"airCleaner_smartHome","search_semantic":{"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"},"state":{"fg::airCleaner_smartHome::default::default":{"state":"default"}},"text":"最小风","array_index":0,"operation":"SET","cid":"cida15e427c@dx00990e7e8163000000"}
             */

            private IntentBean intent;

            @NoArgsConstructor
            @Data
            public static class IntentBean {
                /**
                 * semantic : {"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}}
                 * dialog_stat : dataInvalid
                 * demand_semantic : {"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"}
                 * save_history : true
                 * uuid : atn034d4688@dx00070e7e8ba5a10e01
                 * engine_time : 12.645
                 * sid : cida15e427c@dx00990e7e8ba3010002
                 * rc : 0
                 * score : 1
                 * used_state : {"state_key":"fg::airCleaner_smartHome::default::default","state":"default"}
                 * answer : {"text":"已为您将空气净化器风速设置为\"低速\""}
                 * orig_semantic : {"slots":{"attrValue":"低速","attr":"风速","attrType":"String"}}
                 * service : airCleaner_smartHome
                 * search_semantic : {"service":"airCleaner_smartHome","attrValue":"低速","attr":"风速","attrType":"String"}
                 * state : {"fg::airCleaner_smartHome::default::default":{"state":"default"}}
                 * text : 最小风
                 * array_index : 0
                 * operation : SET
                 * cid : cida15e427c@dx00990e7e8163000000
                 */

                private SemanticBean semantic;
                private String dialogStat;
                private DemandSemanticBean demandSemantic;
                private boolean saveHistory;
                private String uuid;
                private double engineTime;
                private String sid;
                private int rc;
                private int score;
                private UsedStateBean usedState;
                private AnswerBean answer;
                private OrigSemanticBean origSemantic;
                private String service;
                private SearchSemanticBean searchSemantic;
                private StateBean state;
                private String text;
                private int arrayIndex;
                private String operation;
                private String cid;

                @NoArgsConstructor
                @Data
                public static class SemanticBean {
                    /**
                     * slots : {"attrValue":"低速","attr":"风速","attrType":"String"}
                     */

                    private SlotsBean slots;

                    @NoArgsConstructor
                    @Data
                    public static class SlotsBean {
                        /**
                         * attrValue : 低速
                         * attr : 风速
                         * attrType : String
                         */

                        private String attrValue;
                        private String attr;
                        private String attrType;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class DemandSemanticBean {
                    /**
                     * service : airCleaner_smartHome
                     * attrValue : 低速
                     * attr : 风速
                     * attrType : String
                     */

                    private String service;
                    private String attrValue;
                    private String attr;
                    private String attrType;
                }

                @NoArgsConstructor
                @Data
                public static class UsedStateBean {
                    /**
                     * state_key : fg::airCleaner_smartHome::default::default
                     * state : default
                     */

                    private String stateKey;
                    private String state;
                }

                @NoArgsConstructor
                @Data
                public static class AnswerBean {
                    /**
                     * text : 已为您将空气净化器风速设置为"低速"
                     */

                    private String text;
                }

                @NoArgsConstructor
                @Data
                public static class OrigSemanticBean {
                    /**
                     * slots : {"attrValue":"低速","attr":"风速","attrType":"String"}
                     */

                    private SlotsBeanX slots;

                    @NoArgsConstructor
                    @Data
                    public static class SlotsBeanX {
                        /**
                         * attrValue : 低速
                         * attr : 风速
                         * attrType : String
                         */

                        private String attrValue;
                        private String attr;
                        private String attrType;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class SearchSemanticBean {
                    /**
                     * service : airCleaner_smartHome
                     * attrValue : 低速
                     * attr : 风速
                     * attrType : String
                     */

                    private String service;
                    private String attrValue;
                    private String attr;
                    private String attrType;
                }

                // FIXME generate failure  field _$FgAirCleanerSmartHomeDefaultDefault50
                @NoArgsConstructor
                @Data
                public static class StateBean {
                }
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class UserParamsBean {
        /**
         * SN_MideaFan : 0000FA111AMS125H0186151100010000
         */

        private String SNMideaFan;
    }
}
