<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hangman statistics</title>
</head>
<body>
<h1>
    Top 10 players
</h1>
<ol>
    <#list players as player>
    <li>Username: ${player.name} - ${player.highscore}</li>
        </#list>
</ol>

</body>
</html>