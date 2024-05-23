import styles from "./index.module.scss";
import Recommendation from "../../components/recommendation";
import {Dispatch, SetStateAction} from "react";

function MainPage({userId, pageFunc, productFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>, productFunc: Dispatch<SetStateAction<string>>}) {
    return (
        <div>
            <div className={styles["app-body"]}>
                <Recommendation userId={userId} pageFunc={pageFunc} productFunc={productFunc}/>
            </div>
        </div>
    )
}

export default MainPage;