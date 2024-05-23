import styles from "../index.module.scss";
import React, {Dispatch, SetStateAction, useState} from "react";
import api from "../../../api";

function Signup({userIDFunc, pageFunc}: {userIDFunc: Dispatch<SetStateAction<string>>, pageFunc: Dispatch<SetStateAction<string>>}) {

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const checkPasswordsAreEqual = (password: string, confirmPassword: string) => {
        return password === confirmPassword;
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (!checkPasswordsAreEqual(password, confirmPassword)) {
            //TODO: Show error message in the screen
            console.log('Passwords do not match');
            return;
        }

        api.post('register', {"username": username, "password": password, "email": email})
            .then(res => {
                if (res.status === 200) {
                    userIDFunc(res.data["userID"]);
                    pageFunc("main");
                }
            }).catch(err => {
                console.error(err);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className={styles["info-text"]}>
                Username
            </div>
            <input type={"text"} value={username} onChange={(e) => setUsername(e.target.value)}
                   className={styles["info-bar"]}/>

            <div className={styles["info-text"]}>
                Email
            </div>
            <input type={"text"} value={email} onChange={(e) => setEmail(e.target.value)}
                   className={styles["info-bar"]}/>

            <div className={styles["info-text"]}>
                Password
            </div>
            <input type={"password"} value={password} onChange={(e) => setPassword(e.target.value)}
                   className={styles["info-bar"]}/>

            <div className={styles["info-text"]}>
                Confirm Password
            </div>
            <input type={"password"} value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}
                   className={styles["info-bar"]}/>

            <button type='submit' className={styles["submit-button"]}>
                <div className={styles["tab-text"]}>Submit</div>
            </button>
        </form>
    )
}

export default Signup;