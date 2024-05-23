import React from "react";
import styles from "./index.module.scss";

function Image({image} : {image: string}) {
    //TODO: Take images from the backend
    return (
        <div className={styles['general-container']}>
            <img className={styles['image']} src="/attachment.png" alt="Image"/>
            <button className={styles['delete-button']}>
                <img className={styles['icon']} src="/trashcan.png" alt="Delete"/>
            </button>
        </div>
    );
}
export default Image;