package com.bw.bean;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/2 21:21:34
 * 功能:
 */
public class Music {

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {


        private String pic_s210;
        private String name;
        private List<ContentBean> content;

        public String getPic_s210() {
            return pic_s210;
        }

        public void setPic_s210(String pic_s210) {
            this.pic_s210 = pic_s210;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {

            private String author;
            private String pic_small;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getPic_small() {
                return pic_small;
            }

            public void setPic_small(String pic_small) {
                this.pic_small = pic_small;
            }

            public ContentBean(String author, String pic_small) {
                this.author = author;
                this.pic_small = pic_small;
            }
        }
    }
}
