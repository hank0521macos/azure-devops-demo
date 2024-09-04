<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Snake Game - Welcome</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        form, #resetButton {
            margin-top: 20px;
        }
        input, button {
            font-size: 18px;
            padding: 10px;
        }
        #highScores {
            margin-top: 20px;
            text-align: center;
        }
        table {
            border-collapse: collapse;
            width: 300px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
    <h1>貪吃狗拉拉</h1>
    <form action="/game" method="get">
        <input type="text" name="playerName" placeholder="輸入你的名字" required>
        <button type="submit">開始遊戲</button>
    </form>
    <div id="highScores">
        <h2>排行榜</h2>
        <table>
            <thead>
                <tr>
                    <th>排名</th>
                    <th>玩家</th>
                    <th>分數</th>
                </tr>
            </thead>
            <tbody id="highScoresBody">
            </tbody>
        </table>
    </div>
    <button id="resetButton">重置排行榜</button>
    <script>
        function loadHighScores() {
            fetch('/highScores')
                .then(response => response.json())
                .then(scores => {
                    const tbody = document.getElementById('highScoresBody');
                    tbody.innerHTML = '';
                    scores.forEach((score, index) => {
                        const row = tbody.insertRow();
                        row.insertCell(0).textContent = index + 1;
                        row.insertCell(1).textContent = score.playerName;
                        row.insertCell(2).textContent = score.score;
                    });
                });
        }

        document.getElementById('resetButton').addEventListener('click', function() {
            if (confirm('確定要重置排行榜嗎？此操作不可撤銷。')) {
                fetch('/resetHighScores', { method: 'POST' })
                    .then(response => response.json())
                    .then(result => {
                        if (result.success) {
                            alert('排行榜已重置');
                            loadHighScores();
                        } else {
                            alert('重置排行榜失敗');
                        }
                    });
            }
        });

        loadHighScores();
    </script>
</body>
</html>