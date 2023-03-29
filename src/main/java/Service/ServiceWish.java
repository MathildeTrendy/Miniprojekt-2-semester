package Service;

import com.example.MiniProject.Model.User;
import com.example.MiniProject.Repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWish {

    @Autowired
    private WishRepository wishRepository;

    public void createUser(User user) throws InvalidInputException {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidInputException("All fields must be filled");
        }

        wishRepository.createUser(user);
    }

    public class InvalidInputException extends Exception {
        public InvalidInputException(String errorMessage) {
            super(errorMessage);
        }
    }
}





