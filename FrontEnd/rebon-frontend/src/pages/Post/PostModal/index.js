import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import '../../../styles/post-modal.css';
import AWS from 'aws-sdk';

export default function PostModal() {
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);

  function toggleModal(e) {
    setOpacity(0);
    setIsOpen(!isOpen);
  }

  function afterOpen() {
    setTimeout(() => {
      setOpacity(1);
    }, 100);
  }

  function beforeClose() {
    return new Promise((resolve) => {
      setOpacity(0);
      setTimeout(resolve, 300);
    });
  }

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  return (
    <ModalProvider backgroundComponent={FadingBackground}>
      {/* <div className="post-modal-click" onClick={toggleModal}>
        작성완료
      </div> */}

      <StyledModal
        isOpen={isOpen}
        afterOpen={afterOpen}
        beforeClose={beforeClose}
        onBackgroundClick={toggleModal}
        onEscapeKeydown={toggleModal}
        opacity={opacity}
        backgroundProps={{ opacity }}
      >
        <div className="post-modal-wrapper">
          <button className="close" onClick={toggleModal}>
            <Link to="/mypage/footprint" style={{ color: 'inherit', textDecoration: 'none' }}>
              <FontAwesomeIcon icon={faXmark} />
            </Link>
          </button>
          <img className="post-modal-image" alt="review-image" src="image/reviewLogo.png" />
          <div className="post-modal-notice">리뷰가 등록되었습니다.</div>
        </div>
      </StyledModal>
    </ModalProvider>
  );
}

const StyledModal = Modal.styled`
  width: 21rem;
  height: 16rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
