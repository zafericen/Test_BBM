import styles from "./index.module.scss";
import LoginSignup from "../../components/login-signup";
import {Dispatch, SetStateAction} from "react";

function LoginSignupPage({userIDFunc, pageFunc}: {userIDFunc: Dispatch<SetStateAction<string>>, pageFunc: Dispatch<SetStateAction<string>>}) {
    return (
        <div>
            <div className={styles["app-body"]}>
                <LoginSignup userIDFunc={userIDFunc} pageFunc={pageFunc}/>
            </div>
        </div>
    )
}

export default LoginSignupPage;