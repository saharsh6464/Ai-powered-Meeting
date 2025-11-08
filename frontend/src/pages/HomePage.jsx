import React, { useState } from 'react';
import './HomePage.css';
import { useNavigate } from 'react-router-dom';

function HomePage() {
  const [roomCode, setRoomCode] = useState('');
  const [newRoomCode, setNewRoomCode] = useState('');
  const navigate = useNavigate();

  // Join existing room
  function handleJoinRoom(ev) {
    ev.preventDefault();
    if (roomCode.trim() !== '') {
      navigate(`/room/${roomCode}`);
    }
  }

  // Create room with custom room ID
  function handleCreateRoom(ev) {
    ev.preventDefault();
    if (newRoomCode.trim() !== '') {
      navigate(`/room/${newRoomCode}`);
    }
  }

  return (
    <div className="home-page">
      {/* Join Existing Room */}
      <form className="form" onSubmit={handleJoinRoom}>
        <label>Enter Existing Room Code</label>
        <input
          type="text"
          placeholder="Enter room code"
          value={roomCode}
          onChange={(e) => setRoomCode(e.target.value)}
          required
        />
        <button type="submit">Join Room</button>
      </form>

      <div className="or">──────── OR ────────</div>

      {/* Create New Room Manually */}
      <form className="form" onSubmit={handleCreateRoom}>
        <label>Create New Room (Enter Your Room ID)</label>
        <input
          type="text"
          placeholder="Type custom room ID"
          value={newRoomCode}
          onChange={(e) => setNewRoomCode(e.target.value)}
          required
        />
        <button type="submit" className="create-room-btn">Create Room</button>
      </form>
    </div>
  );
}

export default HomePage;
