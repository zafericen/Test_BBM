import React, {Dispatch, SetStateAction, useState} from "react";
import styles from "./index.module.scss";
import Image from "../../image";

function getStarsDiv(rating: number, setRating: Dispatch<SetStateAction<number>>) {
    let stars: string[] = [];

    while (rating >= 1) {
        stars.push("/star-full.png");
        rating = rating - 1;
    }

    while (stars.length < 5) {
        stars.push("/star-empty.png");
    }

    let divs: JSX.Element[] = [];

    //for every star, create button with image that adjusts rating when clicked\
    for (let i = 0; i < stars.length; i++) {
        divs.push(<button className={styles['star-button']} onClick={() => setRating(i + 1)}><img src={stars[i]} className={styles["star-icon"]}/>
        </button>)
    }

    return divs;
}

    function AddComment({userId, pageFunc} : {userId: String, pageFunc: Dispatch<SetStateAction<string>>}) {
    //TODO: Take images and categories from the backend
   
    const [rating, setRating] = useState(0);
    
    return (
        <div className={styles['general-container']}>
            <div className={styles['rating']}>
                <div className={styles.stars}>
                    {getStarsDiv(rating, setRating)}
                </div>
            </div>
            <textarea className={styles['description-box']} placeholder={'Type your thoughts about the product'}/>
            <div className={styles['images-container']}>
                <Image image={'/image1.jpg'}/>
                <Image image={'/image2.jpg'}/>
                <Image image={'/image3.jpg'}/>
                <Image image={'/image4.jpg'}/>
                <Image image={'/image5.jpg'}/>
            </div>
            <div className={styles['buttons-container']}>
                <button onClick={() => {pageFunc("product");}} className={styles['cancel-button']}>
                    <div className={styles['button-text']}>Cancel</div>
                    <img className={styles['icon']} src="/trashcan.png" alt="Upload Image"/>
                </button>
                <button className={styles['upload-image-button']}>
                    <div className={styles['button-text']}>Upload Image</div>
                    <img className={styles['icon']} src="/attachment.png" alt="Upload Image"/>
                </button>
                <button className={styles['submit-button']}>
                    <div className={styles['button-text']}>Submit</div>
                </button>
            </div>
        </div>
    );
}

export default AddComment;