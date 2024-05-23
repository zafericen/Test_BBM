import {Dispatch, SetStateAction, useState} from "react";
import styles from "./index.module.scss";
import texts from "../../styles/texts.scss";
import Login from "./login";
import Signup from "./signup";

const loginTabLogin = texts["login-tab-login-text"];
const loginTabSignup = texts["login-tab-signup-text"];

function LoginSignup({userIDFunc, pageFunc}: {userIDFunc: Dispatch<SetStateAction<string>>, pageFunc: Dispatch<SetStateAction<string>>}) {
    const [isSigned, setIsSigned] = useState(false);

    return (
        <div className={isSigned ? styles["container-login"] : styles["container-signup"]}>
            <div className={styles["tab-row"]}>
                <button className={isSigned ? styles["tab-selected"] : styles["tab-not-selected"]} onClick={() => setIsSigned(!isSigned)}>
                    <div className={styles["tab-text"]}>
                        {loginTabLogin}
                    </div>
                </button>
                <button className={isSigned ? styles["tab-not-selected"] : styles["tab-selected"]} onClick={() => setIsSigned(!isSigned)}>
                    <div className={styles["tab-text"]}>
                        {loginTabSignup}
                    </div>
                </button>
            </div>
            <div className={styles["info-container"]}>
                {isSigned ? <Login userIDFunc={userIDFunc} pageFunc={pageFunc}/> : <Signup userIDFunc={userIDFunc} pageFunc={pageFunc}/>}
            </div>
        </div>
    )
}

export default LoginSignup;