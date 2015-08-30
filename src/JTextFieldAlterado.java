import java.awt.event.KeyEvent;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class JTextFieldAlterado extends JTextField{
	
		private int maximoCaracteres = -1;
		
		/** Construtor de um JTextField que só aceita números.*/
		public JTextFieldAlterado() {
	        super();
	        addKeyListener(new java.awt.event.KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent evt) {
	            	jTextFieldKeyTyped(evt);
	            }	        	
        	});
    	}

		/** Construtor de um JTextField que não aceita números.*/
		public JTextFieldAlterado(int maximo) {    
		    super();
		    setMaximoCaracteres(maximo);
		  
			addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent evt) {
					jTextFieldKeyTyped(evt);
				}
			});
		}
		  
	private void jTextFieldKeyTyped(KeyEvent evt) {
      
		String caracteres = "0987654321";
		if (!caracteres.contains(evt.getKeyChar() + "")) {
			evt.consume();
		}
		if((getText().length() >= getMaximoCaracteres()) && (getMaximoCaracteres() != -1)){
			evt.consume();
			setText(getText().substring(0,getMaximoCaracteres()));
		}
    }

    public int getMaximoCaracteres() {
        return maximoCaracteres;
    }
    public void setMaximoCaracteres(int maximoCaracteres) {
        this.maximoCaracteres = maximoCaracteres;
    }
}

