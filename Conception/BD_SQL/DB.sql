--enums

CREATE TYPE roleEnum AS ENUM ('ADMIN', 'EMPLOYEE');
CREATE TYPE statusEnum AS ENUM ('COMPLETED', 'INPROGRESS', 'NOTSTARTED', 'CANCELLED');
CREATE TYPE priorityEnum AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'URGENT');

--tables

CREATE TABLE person (
    idPerson SERIAL PRIMARY KEY,
    prenom VARCHAR(100) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL,
    tel VARCHAR(20)
);

CREATE TABLE userTable (
    idUser INTEGER PRIMARY KEY REFERENCES person(idPerson) ON DELETE CASCADE,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role roleEnum NOT NULL DEFAULT 'EMPLOYEE'
);

CREATE TABLE task (
    idTask SERIAL PRIMARY KEY,
    taskDescription TEXT NOT NULL,
    taskName VARCHAR(200) NOT NULL,
    taskStatus statusEnum NOT NULL DEFAULT 'NOTSTARTED',
    deadline DATE,
    priority priorityEnum NOT NULL DEFAULT 'MEDIUM',
    createdBy INT REFERENCES userTable(idUser) ON DELETE SET NULL,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    startDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT deadlineCheck CHECK (deadline >= CURRENT_DATE OR deadline IS NULL) --'OR'to not have an effect on existing tasks
);

CREATE TABLE team(
    idTeam SERIAL PRIMARY KEY,
    teamName TEXT NOT NULL,
    teamDescription TEXT NOT NULL
);

CREATE TABLE project(
    idProject SERIAL PRIMARY KEY,
    projectName TEXT NOT NULL,
    projectDescription TEXT NOT NULL,
    projectStatus statusEnum NOT NULL DEFAULT 'NOTSTARTED',
    startDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    endDate TIMESTAMP,

    CONSTRAINT checkDates CHECK (startDate <= endDate)
);

CREATE TABLE taskComments(
    idComment SERIAL PRIMARY KEY,
    commentBody TEXT NOT NULL,
    commentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idUser INT,
    idTask INT,

    FOREIGN KEY (idUser) REFERENCES userTable(idUser) ON DELETE SET NULL, --in case the user is deleted
    FOREIGN KEY (idTask) REFERENCES task(idTask)
);

CREATE TABLE teamMembers(
    idUser INT,
    idTeam INT,

    PRIMARY KEY (idUser,idTeam),
    FOREIGN KEY (idUser) REFERENCES userTable(idUser),
    FOREIGN KEY (idTeam) REFERENCES team(idTeam)
);

CREATE TABLE assignedTasks(
    idUser INT,
    idTask INT,
    
    PRIMARY KEY (idUser,idTask),
    FOREIGN KEY (idUser) REFERENCES userTable(idUser),
    FOREIGN KEY (idTask) REFERENCES task(idTask)
);

CREATE TABLE projectTeams(
    idTeam INT,
    idProject INT,

    PRIMARY KEY (idTeam,idProject),
    FOREIGN KEY (idTeam) REFERENCES team(idTeam),
    FOREIGN KEY (idProject) REFERENCES project(idProject)
);

CREATE TABLE projectTasks(
    idTask INT,
    idProject INT,

    PRIMARY KEY (idTask,idProject),
    FOREIGN KEY (idProject) REFERENCES project(idProject),
    FOREIGN KEY (idTask) REFERENCES task(idTask)
);
 
--triggers

CREATE OR REPLACE FUNCTION updateTime()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updatedAt = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER taskUpdate
    BEFORE UPDATE ON task
    FOR EACH ROW
    EXECUTE FUNCTION updateTime();
 
