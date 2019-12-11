package com.tayo.patient.model;

import java.io.Serializable;

public class User  implements Serializable {


        private String fullName;
        private String userName;
        private String title;
        private String email;
        private String hospital;
        private String phoneNumber;
        private String password;


        public User() {
        }


        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        @Override
        public String toString() {
            return "User{" +
                    "fullName='" + fullName + '\'' +
                    ", userName='" + userName + '\'' +
                    ", title='" + title + '\'' +
                    ", email='" + email + '\'' +
                    ", hospital='" + hospital + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


