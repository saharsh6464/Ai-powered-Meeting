import React, { useEffect, useRef } from "react";
import { ZegoUIKitPrebuilt } from "@zegocloud/zego-uikit-prebuilt";
import { useParams } from "react-router-dom";

function RoomPage() {
  const { roomId } = useParams();
  const meetingRef = useRef(null);

  // ✅ Function to call backend and start recording
  const startCloudRecording = async () => {
    try {
      await fetch("http://localhost:8080/api/start-recording", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ roomId: roomId })
      });
      console.log("✅ Cloud recording started for room:", roomId);
    } catch (err) {
      console.error("❌ Failed to start cloud recording:", err);
    }
  };

  // ✅ Initialize Zego Meeting
  useEffect(() => {
    const setupMeeting = async () => {
      const appID = 345513070; // Replace with your Zego App ID
      const serverSecret = "82eb82e3c4c89e68d55d2ce09d93fa28"; // Test use only

      const kitToken = ZegoUIKitPrebuilt.generateKitTokenForTest(
        appID,
        serverSecret,
        roomId,
        Date.now().toString(),
        "User_" + Math.floor(Math.random() * 1000)
      );

      const zc = ZegoUIKitPrebuilt.create(kitToken);

      zc.joinRoom({
        container: meetingRef.current,
        scenario: { mode: ZegoUIKitPrebuilt.VideoConference },
        showScreenSharingButton: true,
        onReady: () => {
          console.log("✅ User joined room. Starting cloud recording...");
          startCloudRecording(); // ✅ Start cloud recording when room is ready
        }
      });
    };

    setupMeeting();
  }, [roomId]);

  return (
    <div className="room-page">
      {/* Zego Meeting UI */}
      <div ref={meetingRef} style={{ width: "100%", height: "100vh" }} />
    </div>
  );
}

export default RoomPage;
