package com.example.MiniProject.Repository;
import com.example.MiniProject.Utility.LoginSampleException;
import com.example.MiniProject.Model.User;

public interface IRepository {
    User login(String email, String password) throws LoginSampleException;

}
