import styles from "../index.module.scss";
import React, {Dispatch, SetStateAction, useState} from "react";
import api from "../../../api";

function Login({userIDFunc, pageFunc}: {userIDFunc: Dispatch<SetStateAction<string>>, pageFunc: Dispatch<SetStateAction<string>>}) {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    //TODO: Encrypt password before sending it to the server

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        api.post('login', {"username": username, "password": password})
            .then(res => {
                if (res.status === 200) {
                    userIDFunc(res.data["userID"]);
                    pageFunc(res.data["userID"] === "MERCHANT" ? "merchant-dashboard" : "main");
                }
            }).catch(err => {
                console.error(err);
            });
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className={styles["info-text"]}>
                Username
            </div>
            <input type={"text"} value={username} onChange={(e) => setUsername(e.target.value)}
                   className={styles["info-bar"]}/>

            <div className={styles["info-text"]}>
                Password
            </div>
            <input type={"password"} value={password} onChange={(e) => setPassword(e.target.value)}
                   className={styles["info-bar"]}/>

            <button type='submit' className={styles["submit-button"]}>
                <div className={styles["tab-text"]}>Submit</div>
            </button>
        </form>
    )
}

export default Login;