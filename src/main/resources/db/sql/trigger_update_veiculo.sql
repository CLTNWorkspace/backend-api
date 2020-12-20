CREATE TRIGGER atualiza_veiculo
BEFORE UPDATE ON veiculo
FOR EACH ROW
EXECUTE PROCEDURE data_atualizacao();